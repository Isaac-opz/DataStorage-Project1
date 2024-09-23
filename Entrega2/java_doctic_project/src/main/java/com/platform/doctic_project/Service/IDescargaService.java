package com.platform.doctic_project.Service;


import com.platform.doctic_project.Model.Descarga;

import java.util.List;

public interface IDescargaService {
    void recordDocumentDownload(Integer documentId, Integer userId);
    List<Descarga> getUserDownloadHistory(Integer userId);
}
