package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Descarga;

public interface DownloadService {
    void recordDocumentDownload(Integer documentId, Integer userId);
    List<Descarga> getUserDownloadHistory(Integer userId);
}
