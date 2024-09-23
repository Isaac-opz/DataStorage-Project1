package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Comentario;

public interface IComentarioService {
    Comentario addComment(Integer documentId, Integer userId, String content);
    Comentario replyToComment(Integer commentId, Integer userId, String content);
}
