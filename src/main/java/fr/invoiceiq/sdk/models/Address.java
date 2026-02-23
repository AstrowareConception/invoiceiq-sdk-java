package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Représente une adresse postale.
 */
public class Address {
    @SerializedName("line1")
    private String line1;

    @SerializedName("line2")
    private String line2;

    @SerializedName("postCode")
    private String postCode;

    @SerializedName("city")
    private String city;

    @SerializedName("countryCode")
    private String countryCode;

    public Address() {
    }

    public Address(String line1, String line2, String postCode, String city, String countryCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.postCode = postCode;
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String line1;
        private String line2;
        private String postCode;
        private String city;
        private String countryCode;

        public Builder line1(String line1) {
            this.line1 = line1;
            return this;
        }

        public Builder line2(String line2) {
            this.line2 = line2;
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

        public Address build() {
            return new Address(line1, line2, postCode, city, countryCode);
        }
    }
}
