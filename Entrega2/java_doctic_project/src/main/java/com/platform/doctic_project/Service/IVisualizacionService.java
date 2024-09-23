package com.platform.doctic_project.Service;

import java.util.List;

public interface IVisualizacionService {
    void recordDocumentView(Long userId, Long documentId);
    List<Long> getUserViewHistory(Long userId);
}
