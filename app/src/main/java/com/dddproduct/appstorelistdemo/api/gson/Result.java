
package com.dddproduct.appstorelistdemo.api.gson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("appletvScreenshotUrls")
    @Expose
    private List<Object> appletvScreenshotUrls = null;
    @SerializedName("ipadScreenshotUrls")
    @Expose
    private List<String> ipadScreenshotUrls = null;
    @SerializedName("artworkUrl512")
    @Expose
    private String artworkUrl512;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("screenshotUrls")
    @Expose
    private List<String> screenshotUrls = null;
    @SerializedName("advisories")
    @Expose
    private List<Object> advisories = null;
    @SerializedName("supportedDevices")
    @Expose
    private List<String> supportedDevices = null;
    @SerializedName("isGameCenterEnabled")
    @Expose
    private Boolean isGameCenterEnabled;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("features")
    @Expose
    private List<String> features = null;
    @SerializedName("averageUserRatingForCurrentVersion")
    @Expose
    private Double averageUserRatingForCurrentVersion;
    @SerializedName("languageCodesISO2A")
    @Expose
    private List<String> languageCodesISO2A = null;
    @SerializedName("fileSizeBytes")
    @Expose
    private String fileSizeBytes;
    @SerializedName("userRatingCountForCurrentVersion")
    @Expose
    private Integer userRatingCountForCurrentVersion;
    @SerializedName("trackContentRating")
    @Expose
    private String trackContentRating;
    @SerializedName("contentAdvisoryRating")
    @Expose
    private String contentAdvisoryRating;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("currentVersionReleaseDate")
    @Expose
    private String currentVersionReleaseDate;
    @SerializedName("primaryGenreId")
    @Expose
    private Integer primaryGenreId;
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("minimumOsVersion")
    @Expose
    private String minimumOsVersion;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("bundleId")
    @Expose
    private String bundleId;
    @SerializedName("releaseNotes")
    @Expose
    private String releaseNotes;
    @SerializedName("genreIds")
    @Expose
    private List<String> genreIds = null;
    @SerializedName("isVppDeviceBasedLicensingEnabled")
    @Expose
    private Boolean isVppDeviceBasedLicensingEnabled;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("sellerName")
    @Expose
    private String sellerName;
    @SerializedName("formattedPrice")
    @Expose
    private String formattedPrice;
    @SerializedName("averageUserRatingForCurrentVersioning")
    @Expose
    private Double averageUserRatingForCurrentVersioning;
    @SerializedName("userRatingCount")
    @Expose
    private Integer userRatingCount;

    public List<Object> getAppletvScreenshotUrls() {
        return appletvScreenshotUrls;
    }

    public void setAppletvScreenshotUrls(List<Object> appletvScreenshotUrls) {
        this.appletvScreenshotUrls = appletvScreenshotUrls;
    }

    public List<String> getIpadScreenshotUrls() {
        return ipadScreenshotUrls;
    }

    public void setIpadScreenshotUrls(List<String> ipadScreenshotUrls) {
        this.ipadScreenshotUrls = ipadScreenshotUrls;
    }

    public String getArtworkUrl512() {
        return artworkUrl512;
    }

    public void setArtworkUrl512(String artworkUrl512) {
        this.artworkUrl512 = artworkUrl512;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public List<String> getScreenshotUrls() {
        return screenshotUrls;
    }

    public void setScreenshotUrls(List<String> screenshotUrls) {
        this.screenshotUrls = screenshotUrls;
    }

    public List<Object> getAdvisories() {
        return advisories;
    }

    public void setAdvisories(List<Object> advisories) {
        this.advisories = advisories;
    }

    public List<String> getSupportedDevices() {
        return supportedDevices;
    }

    public void setSupportedDevices(List<String> supportedDevices) {
        this.supportedDevices = supportedDevices;
    }

    public Boolean getIsGameCenterEnabled() {
        return isGameCenterEnabled;
    }

    public void setIsGameCenterEnabled(Boolean isGameCenterEnabled) {
        this.isGameCenterEnabled = isGameCenterEnabled;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public Double getAverageUserRatingForCurrentVersion() {
        return averageUserRatingForCurrentVersion;
    }

    public void setAverageUserRatingForCurrentVersion(Double averageUserRatingForCurrentVersion) {
        this.averageUserRatingForCurrentVersion = averageUserRatingForCurrentVersion;
    }

    public List<String> getLanguageCodesISO2A() {
        return languageCodesISO2A;
    }

    public void setLanguageCodesISO2A(List<String> languageCodesISO2A) {
        this.languageCodesISO2A = languageCodesISO2A;
    }

    public String getFileSizeBytes() {
        return fileSizeBytes;
    }

    public void setFileSizeBytes(String fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    public Integer getUserRatingCountForCurrentVersion() {
        return userRatingCountForCurrentVersion;
    }

    public void setUserRatingCountForCurrentVersion(Integer userRatingCountForCurrentVersion) {
        this.userRatingCountForCurrentVersion = userRatingCountForCurrentVersion;
    }

    public String getTrackContentRating() {
        return trackContentRating;
    }

    public void setTrackContentRating(String trackContentRating) {
        this.trackContentRating = trackContentRating;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCurrentVersionReleaseDate() {
        return currentVersionReleaseDate;
    }

    public void setCurrentVersionReleaseDate(String currentVersionReleaseDate) {
        this.currentVersionReleaseDate = currentVersionReleaseDate;
    }

    public Integer getPrimaryGenreId() {
        return primaryGenreId;
    }

    public void setPrimaryGenreId(Integer primaryGenreId) {
        this.primaryGenreId = primaryGenreId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getMinimumOsVersion() {
        return minimumOsVersion;
    }

    public void setMinimumOsVersion(String minimumOsVersion) {
        this.minimumOsVersion = minimumOsVersion;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getReleaseNotes() {
        return releaseNotes;
    }

    public void setReleaseNotes(String releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<String> genreIds) {
        this.genreIds = genreIds;
    }

    public Boolean getIsVppDeviceBasedLicensingEnabled() {
        return isVppDeviceBasedLicensingEnabled;
    }

    public void setIsVppDeviceBasedLicensingEnabled(Boolean isVppDeviceBasedLicensingEnabled) {
        this.isVppDeviceBasedLicensingEnabled = isVppDeviceBasedLicensingEnabled;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public Double getAverageUserRatingForCurrentVersioning() {
        return averageUserRatingForCurrentVersioning;
    }

    public void setAverageUserRatingForCurrentVersioning(Double averageUserRatingForCurrentVersioning) {
        this.averageUserRatingForCurrentVersioning = averageUserRatingForCurrentVersioning;
    }

    public Integer getUserRatingCount() {
        return userRatingCount;
    }

    public void setUserRatingCount(Integer userRatingCount) {
        this.userRatingCount = userRatingCount;
    }

}
