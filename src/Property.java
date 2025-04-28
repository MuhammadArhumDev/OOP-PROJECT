public class Property {

    private String propertyID;
    private String propertyName;
    private String propertyDescription;
    private String propertyLocation;
    private Double propertyArea;
    private Double propertyPrice;

    public Property() {
        this.propertyID = "";
        this.propertyName = "";
        this.propertyDescription = "";
        this.propertyLocation = "";
        this.propertyArea = 0.0;
        this.propertyPrice = 0.0;
    }

    public Property(String propertyID, String propertyName, String propertyDescription, String propertyLocation, Double propertyArea, Double propertyPrice) {
        this.propertyID = propertyID;
        this.propertyName = propertyName;
        this.propertyDescription = propertyDescription;
        this.propertyLocation = propertyLocation;
        this.propertyArea = propertyArea;
        this.propertyPrice = propertyPrice;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public Double getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(Double propertyArea) {
        this.propertyArea = propertyArea;
    }

    public Double getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(Double propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

}
