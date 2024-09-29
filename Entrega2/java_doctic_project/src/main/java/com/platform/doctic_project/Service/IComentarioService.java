package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Comentario;

public interface IComentarioService {

    List<Comentario> listDocumentComments(Integer documentId);

    Comentario addComment(Comentario comentario);

    void deleteComment(Integer commentId);
}
