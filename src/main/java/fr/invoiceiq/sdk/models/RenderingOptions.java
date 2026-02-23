package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Options de rendu pour la génération de PDF.
 */
public class RenderingOptions {
    @SerializedName("template")
    private String template = "classic-01";

    @SerializedName("font")
    private String font = "Helvetica";

    @SerializedName("primaryColor")
    private String primaryColor = "#0F172A";

    @SerializedName("accentColor")
    private String accentColor = "#2563EB";

    @SerializedName("logo")
    private Logo logo;

    @SerializedName("footer")
    private Footer footer;

    @SerializedName("locale")
    private String locale = "fr-FR";

    @SerializedName("notes")
    private String notes;

    public RenderingOptions() {
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String template = "classic-01";
        private String font = "Helvetica";
        private String primaryColor = "#0F172A";
        private String accentColor = "#2563EB";
        private Logo logo;
        private Footer footer;
        private String locale = "fr-FR";
        private String notes;

        public Builder template(String template) {
            this.template = template;
            return this;
        }

        public Builder font(String font) {
            this.font = font;
            return this;
        }

        public Builder primaryColor(String primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public Builder accentColor(String accentColor) {
            this.accentColor = accentColor;
            return this;
        }

        public Builder logo(Logo logo) {
            this.logo = logo;
            return this;
        }

        public Builder footer(Footer footer) {
            this.footer = footer;
            return this;
        }

        public Builder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public RenderingOptions build() {
            RenderingOptions options = new RenderingOptions();
            options.setTemplate(template);
            options.setFont(font);
            options.setPrimaryColor(primaryColor);
            options.setAccentColor(accentColor);
            options.setLogo(logo);
            options.setFooter(footer);
            options.setLocale(locale);
            options.setNotes(notes);
            return options;
        }
    }

    public static class Logo {
        @SerializedName("url")
        private String url;

        @SerializedName("width")
        private Integer width;

        @SerializedName("align")
        private String align;

        public Logo() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }

        public static LogoBuilder builder() {
            return new LogoBuilder();
        }

        public static class LogoBuilder {
            private String url;
            private Integer width;
            private String align;

            public LogoBuilder url(String url) {
                this.url = url;
                return this;
            }

            public LogoBuilder width(Integer width) {
                this.width = width;
                return this;
            }

            public LogoBuilder align(String align) {
                this.align = align;
                return this;
            }

            public Logo build() {
                Logo logo = new Logo();
                logo.setUrl(url);
                logo.setWidth(width);
                logo.setAlign(align);
                return logo;
            }
        }
    }

    public static class Footer {
        @SerializedName("extraText")
        private String extraText;

        @SerializedName("showPageNumbers")
        private Boolean showPageNumbers = true;

        public Footer() {
        }

        public String getExtraText() {
            return extraText;
        }

        public void setExtraText(String extraText) {
            this.extraText = extraText;
        }

        public Boolean getShowPageNumbers() {
            return showPageNumbers;
        }

        public void setShowPageNumbers(Boolean showPageNumbers) {
            this.showPageNumbers = showPageNumbers;
        }

        public static FooterBuilder builder() {
            return new FooterBuilder();
        }

        public static class FooterBuilder {
            private String extraText;
            private Boolean showPageNumbers = true;

            public FooterBuilder extraText(String extraText) {
                this.extraText = extraText;
                return this;
            }

            public FooterBuilder showPageNumbers(Boolean showPageNumbers) {
                this.showPageNumbers = showPageNumbers;
                return this;
            }

            public Footer build() {
                Footer footer = new Footer();
                footer.setExtraText(extraText);
                footer.setShowPageNumbers(showPageNumbers);
                return footer;
            }
        }
    }
}
