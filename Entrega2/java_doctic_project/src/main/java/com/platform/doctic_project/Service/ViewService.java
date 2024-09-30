package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.VistoPor;

public interface ViewService {
    void recordDocumentView(Integer documentId, Integer userId);
    List<VistoPor> getUserViewHistory(Integer userId);
}
