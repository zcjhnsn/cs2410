package cs2410.assn4.model;

//NOTE: Provide a single image data object model

public class Model {
    /**
     * String value for image URL
     */
    private String url;

    /**
     * String value for image title
     */
    private String title;

    /**
     * Default constructor for a single image data object
     * @param line String{}
     */
    public Model(String[] line) {
        this.url = line[0];
        this.title = line[1];
    }

    /**
     * Function to get image URL
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * Function to get image title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set URL of image
     * @param url String
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Set title of image
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
