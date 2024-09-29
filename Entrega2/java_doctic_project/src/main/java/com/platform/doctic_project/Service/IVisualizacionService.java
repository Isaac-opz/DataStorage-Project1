package com.platform.doctic_project.Service;

import java.util.List;

public interface IVisualizacionService {
    void recordDocumentView(Integer userId, Integer documentId);
    List<Integer> getUserViewHistory(Integer userId);
}
