
package com.dddproduct.appstorelistdemo.api.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rights {

    @SerializedName("label")
    @Expose
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}