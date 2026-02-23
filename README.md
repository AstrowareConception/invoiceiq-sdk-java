# InvoiceIQ Java SDK

SDK Java officiel pour l'API InvoiceIQ - Validation, Transformation et Génération de factures Factur-X / ZUGFeRD.

## 🚀 Fonctionnalités

- ✅ **Validation** de documents Factur-X et PDF
- 🔄 **Transformation** de PDF simples en Factur-X (Profil BASIC)
- 📄 **Génération** complète de factures Factur-X avec rendu PDF (Profil EXTENDED)
- 🏗️ **Builders fluent** pour une construction facile des objets
- 🔐 **Authentification** flexible (API Key ou Bearer Token)
- ⚡ **Gestion asynchrone** des jobs avec polling automatique
- 🧪 **Entièrement testable** avec tests unitaires inclus

## 📦 Installation

### Installation locale (sans Maven Central)

#### 1. Cloner ou télécharger le projet

```bash
git clone https://github.com/votre-organisation/invoiceiq-sdk-java.git
cd invoiceiq-sdk-java
```

#### 2. Compiler et installer dans le repository Maven local

```bash
mvn clean install
```

Cette commande va :
- Compiler le SDK
- Exécuter les tests
- Installer le JAR dans votre repository Maven local (`~/.m2/repository/`)

#### 3. Ajouter la dépendance dans votre projet

**Maven** - Ajoutez dans votre `pom.xml` :

```xml
<dependency>
    <groupId>fr.invoiceiq</groupId>
    <artifactId>invoiceiq-sdk-java</artifactId>
    <version>0.1.0</version>
</dependency>
```

**Gradle** - Ajoutez dans votre `build.gradle` :

```gradle
dependencies {
    implementation 'fr.invoiceiq:invoiceiq-sdk-java:0.1.0'
}
```

#### Alternative : Utilisation directe du JAR

Si vous ne voulez pas utiliser Maven/Gradle, vous pouvez utiliser directement le JAR :

1. Compiler le projet :
   ```bash
   mvn clean package
   ```

2. Récupérer le JAR dans `target/invoiceiq-sdk-java-0.1.0.jar`

3. Ajouter le JAR dans le classpath de votre projet avec ses dépendances :
   - `okhttp-4.12.0.jar`
   - `gson-2.10.1.jar`
   - `slf4j-api-2.0.9.jar`
   - `okio-3.x.x.jar` (dépendance d'OkHttp)
   - `kotlin-stdlib-x.x.x.jar` (dépendance d'OkHttp)

## 🔧 Configuration

### Avec API Key (recommandé pour intégrations serveur-à-serveur)

```java
InvoiceIQClient client = InvoiceIQClient.builder()
    .apiKey("your-api-key")
    .build();
```

### Avec Bearer Token (sessions utilisateur)

```java
InvoiceIQClient client = InvoiceIQClient.builder()
    .bearerToken("your-jwt-token")
    .build();
```

### Configuration avancée

```java
InvoiceIQClient client = InvoiceIQClient.builder()
    .apiKey("your-api-key")
    .baseUrl("https://api.invoiceiq.fr")  // URL de l'API
    .connectTimeout(30)                    // Timeout de connexion (secondes)
    .readTimeout(60)                       // Timeout de lecture (secondes)
    .writeTimeout(60)                      // Timeout d'écriture (secondes)
    .build();
```

## 📖 Exemples d'utilisation

### 1. Validation d'un document Factur-X

```java
import fr.invoiceiq.sdk.InvoiceIQClient;
import fr.invoiceiq.sdk.models.ValidationResult;
import java.io.File;

InvoiceIQClient client = InvoiceIQClient.builder()
    .apiKey("your-api-key")
    .build();

File pdfFile = new File("invoice.pdf");
ValidationResult result = client.validation().validate(pdfFile);

System.out.println("Valid: " + result.isValid());
System.out.println("Score: " + result.getScore());
System.out.println("Profile: " + result.getProfile());

if (result.hasIssues()) {
    for (ValidationResult.ValidationIssue issue : result.getIssues()) {
        System.out.println("Issue: " + issue.getMessage());
    }
}
```

### 2. Transformation PDF → Factur-X

```java
import fr.invoiceiq.sdk.InvoiceIQClient;
import fr.invoiceiq.sdk.models.*;
import java.io.File;

// Création des données de facture avec builders fluent
Invoice invoice = Invoice.builder()
    .invoiceNumber("INV-2024-001")
    .issueDate("2024-02-22")
    .currency("EUR")
    .seller(Party.builder()
        .name("My Company SAS")
        .vatId("FR12123456789")
        .countryCode("FR")
        .addressLine1("10 Rue de la Paix")
        .city("Paris")
        .postCode("75001")
        .build())
    .buyer(Party.builder()
        .name("Customer Corp")
        .countryCode("FR")
        .build())
    .addLine(InvoiceLine.builder()
        .id("1")
        .name("Consulting Service")
        .quantity(5)
        .unitCode("HUR")
        .unitPrice(100.00)
        .taxRate(20.00)
        .totalAmount(500.00)
        .build())
    .addTax(TaxSummary.builder()
        .taxRate(20.00)
        .basisAmount(500.00)
        .taxAmount(100.00)
        .build())
    .totalTaxExclusiveAmount(500.00)
    .taxTotalAmount(100.00)
    .totalTaxInclusiveAmount(600.00)
    .build();

// Transformation
File sourcePdf = new File("source.pdf");
Job job = client.transformation().transform(sourcePdf, invoice);

// Attente de la complétion
Job completed = client.transformation().waitForCompletion(
    job.getId(),
    2000,   // Poll toutes les 2 secondes
    60000   // Timeout de 60 secondes
);

// Téléchargement du résultat
if (completed.isCompleted()) {
    File output = new File("output-facturx.pdf");
    client.transformation().downloadResult(completed, output);

    TransformationReport report = client.transformation().downloadReport(completed);
    System.out.println("Score: " + report.getFinalScore());
}
```

### 3. Génération complète d'une facture Factur-X

```java
Invoice invoice = Invoice.builder()
    .invoiceNumber("F-2024-001")
    .issueDate("2024-02-22")
    .dueDate("2024-03-22")
    .currency("EUR")
    .seller(/* ... */)
    .buyer(/* ... */)
    .addLine(/* ... */)
    .addTax(/* ... */)
    .totalTaxExclusiveAmount(1000.00)
    .taxTotalAmount(200.00)
    .totalTaxInclusiveAmount(1200.00)
    .paymentMeans(PaymentMeans.builder()
        .typeCode("30")
        .accountIBAN("FR7630006000011234567890123")
        .accountBIC("AGRIFRPP")
        .accountName("My Company SAS")
        .build())
    .rendering(RenderingOptions.builder()
        .template("classic-01")
        .primaryColor("#1e293b")
        .accentColor("#3b82f6")
        .logo(RenderingOptions.Logo.builder()
            .url("https://example.com/logo.png")
            .width(150)
            .align("left")
            .build())
        .footer(RenderingOptions.Footer.builder()
            .extraText("My Company SAS - SIRET 123456789")
            .showPageNumbers(true)
            .build())
        .locale("fr-FR")
        .notes("Merci pour votre confiance !")
        .build())
    .build();

Job job = client.generation().generate(invoice);
Job completed = client.generation().waitForCompletion(job.getId(), 2000, 120000);

if (completed.isCompleted()) {
    File output = new File("generated-invoice.pdf");
    client.generation().downloadResult(completed, output);
    System.out.println("Invoice generated: " + output.getAbsolutePath());
}
```

### 4. Facture avec exonération de TVA (micro-entrepreneur)

```java
Invoice invoice = Invoice.builder()
    .invoiceNumber("F-2024-MICRO-01")
    .issueDate("2024-02-22")
    .seller(Party.builder()
        .name("Jean Dupont Consultant")
        .registrationId("88990011223344")
        .countryCode("FR")
        .build())
    .buyer(/* ... */)
    .addLine(InvoiceLine.builder()
        .id("1")
        .name("Formation Gestion Projet")
        .quantity(2)
        .unitCode("DAY")
        .unitPrice(450.00)
        .taxRate(0.0)
        .taxCategoryCode("E")  // E = Exonéré
        .taxExemptionReason("TVA non applicable, art. 293 B du CGI")
        .totalAmount(900.00)
        .build())
    .addTax(TaxSummary.builder()
        .taxRate(0.0)
        .taxableAmount(900.00)
        .taxAmount(0.00)
        .taxCategoryCode("E")
        .taxExemptionReason("TVA non applicable, art. 293 B du CGI")
        .build())
    .totalTaxExclusiveAmount(900.00)
    .taxTotalAmount(0.00)
    .totalTaxInclusiveAmount(900.00)
    .notes("Micro-entrepreneur : TVA non applicable, art. 293 B du CGI.")
    .build();
```

## 🧪 Tests

Lancer les tests unitaires :

```bash
mvn test
```

## 📚 Documentation API

### Clients disponibles

- **`client.validation()`** - Validation de documents Factur-X (1 crédit)
- **`client.transformation()`** - Transformation PDF → Factur-X (5 crédits)
- **`client.generation()`** - Génération complète de Factur-X (10 crédits)

### Gestion des erreurs

Le SDK utilise des exceptions typées :

```java
try {
    ValidationResult result = client.validation().validate(file);
} catch (AuthenticationException e) {
    // Problème d'authentification (401)
    System.err.println("Auth error: " + e.getMessage());
} catch (InsufficientCreditsException e) {
    // Crédits insuffisants (402)
    System.err.println("Need " + e.getCreditsRequired() + " credits");
} catch (ValidationException e) {
    // Données invalides (400)
    System.err.println("Invalid data: " + e.getMessage());
} catch (InvoiceIQException e) {
    // Autre erreur API
    System.err.println("API error: " + e.getMessage());
    if (e.hasStatusCode()) {
        System.err.println("HTTP " + e.getStatusCode());
    }
}
```

## 🏗️ Architecture du SDK

```
fr.invoiceiq.sdk
├── InvoiceIQClient          # Client principal
├── config
│   └── InvoiceIQConfig      # Configuration
├── client
│   ├── BaseHttpClient       # Client HTTP de base
│   ├── ValidationClient     # Client de validation
│   ├── TransformationClient # Client de transformation
│   └── GenerationClient     # Client de génération
├── models
│   ├── Invoice              # Modèle de facture
│   ├── Party                # Vendeur/Acheteur
│   ├── InvoiceLine          # Ligne de facture
│   ├── TaxSummary           # Récapitulatif de taxe
│   ├── RenderingOptions     # Options de rendu PDF
│   ├── Job                  # Job asynchrone
│   ├── ValidationResult     # Résultat de validation
│   └── TransformationReport # Rapport de transformation
└── exceptions
    ├── InvoiceIQException           # Exception de base
    ├── AuthenticationException      # Erreur d'auth
    ├── InsufficientCreditsException # Crédits insuffisants
    └── ValidationException          # Données invalides
```

## 🔗 Ressources

- [Documentation API InvoiceIQ](https://www.invoiceiq.fr/docs)
- [Spécifications Factur-X](https://fnfe-mpe.org/factur-x/)
- [Norme EN 16931](https://ec.europa.eu/digital-building-blocks/wikis/display/DIGITAL/Compliance+with+eInvoicing+standard+EN16931)

## 📄 Licence

Proprietary - © 2024 InvoiceIQ

## 🤝 Support

Pour toute question ou problème :
- Email : support@invoiceiq.fr
- Site web : https://www.invoiceiq.fr
