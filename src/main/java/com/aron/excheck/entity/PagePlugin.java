package com.aron.excheck.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public class PagePlugin extends Page {

    private long current;

    private long size;

    private long total;

    private Map<String, Object> pd;

    public PagePlugin() {
    }

    public PagePlugin(long current, long size) {
        super(current, size);
    }

    public PagePlugin(long current, long size, long total) {
        super(current, size, total);
    }

    public PagePlugin(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public PagePlugin(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }

    public Map<String, Object> getPd() {
        return pd;
    }

    public void setPd(Map<String, Object> pd) {
        this.pd = pd;
    }
}
