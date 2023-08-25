package com.jayo.ec04.data.response;

import com.google.gson.annotations.SerializedName;
import com.jayo.ec04.model.Shows;

import java.util.List;

public class ShowResponse {
    @SerializedName("shows")
    public List<Shows> showsList;

    public List<Shows> getShowsList() {
        return showsList;
    }

    public void setShowsList(List<Shows> showsList) {
        this.showsList = showsList;
    }
}
