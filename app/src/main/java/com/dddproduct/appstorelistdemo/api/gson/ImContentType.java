
package com.dddproduct.appstorelistdemo.api.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImContentType {

    @SerializedName("attributes")
    @Expose
    private Attributes__ attributes;

    public Attributes__ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes__ attributes) {
        this.attributes = attributes;
    }

}
