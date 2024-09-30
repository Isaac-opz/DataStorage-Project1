package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Comentario;

public interface CommentService {
    Comentario addComment(Comentario comentario);
    Comentario replyToComment(Integer parentCommentId, Comentario comentario);
    List<Comentario> listDocumentComments(Integer documentId);
    void deleteComment(Integer commentId);
}
