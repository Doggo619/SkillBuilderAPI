package com.base.skillbuilderapi.model.elementProgressApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetElementProgressOutputData {
    @SerializedName("element_progress")
    private List<ElementProgress> elementProgress;
    @SerializedName("elements")
    private List<Element> elements;
    @SerializedName("chapters")
    private List<Chapter> chapters;

    public GetElementProgressOutputData(List<ElementProgress> elementProgress, List<Element> elements, List<Chapter> chapters) {
        this.elementProgress = elementProgress;
        this.elements = elements;
        this.chapters = chapters;
    }

    public List<ElementProgress> getElementProgress() {
        return elementProgress;
    }

    public void setElementProgress(List<ElementProgress> elementProgress) {
        this.elementProgress = elementProgress;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
