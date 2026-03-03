package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Informations détaillées d'une partie pour la génération (format plat avec addressLine1, etc.).
 */
public class PartyDetail {
    @SerializedName("name")
    private String name;

    @SerializedName("registrationId")
    private String registrationId;

    @SerializedName("vatId")
    private String vatId;

    @SerializedName("addressLine1")
    private String addressLine1;

    @SerializedName("addressLine2")
    private String addressLine2;

    @SerializedName("postCode")
    private String postCode;

    @SerializedName("city")
    private String city;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("email")
    private String email;

    public PartyDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getVatId() {
        return vatId;
    }

    public void setVatId(String vatId) {
        this.vatId = vatId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String registrationId;
        private String vatId;
        private String addressLine1;
        private String addressLine2;
        private String postCode;
        private String city;
        private String countryCode;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder registrationId(String registrationId) {
            this.registrationId = registrationId;
            return this;
        }

        public Builder vatId(String vatId) {
            this.vatId = vatId;
            return this;
        }

        public Builder addressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder addressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public PartyDetail build() {
            PartyDetail party = new PartyDetail();
            party.setName(name);
            party.setRegistrationId(registrationId);
            party.setVatId(vatId);
            party.setAddressLine1(addressLine1);
            party.setAddressLine2(addressLine2);
            party.setPostCode(postCode);
            party.setCity(city);
            party.setCountryCode(countryCode);
            party.setEmail(email);
            return party;
        }
    }
}
