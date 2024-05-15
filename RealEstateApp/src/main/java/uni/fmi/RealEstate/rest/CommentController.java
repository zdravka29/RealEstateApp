package uni.fmi.RealEstate.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.RealEstate.dto.CommentDTO;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.models.Article;
import uni.fmi.RealEstate.models.Comment;
import uni.fmi.RealEstate.models.User;
import uni.fmi.RealEstate.services.ApartmentService;
import uni.fmi.RealEstate.services.ArticleService;
import uni.fmi.RealEstate.services.CommentService;
import uni.fmi.RealEstate.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<CommentDTO> findAll() {
        return commentService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{commentId}")
    public CommentDTO getBy(@PathVariable(name = "commentId") long commentId) {
        return convertToDTO(commentService.getEntity(commentId).get());
    }

    @PostMapping()
    public CommentDTO create(@RequestBody CommentDTO commentDTO) {
        validateDTO(commentDTO);
        Comment comment = convertToModel(commentDTO);
        comment.setId(null);
        return convertToDTO(commentService.create(comment));
    }

    private void validateDTO(CommentDTO commentDTO) {
        if ((commentDTO.getArticleId() == 0 && commentDTO.getApartmentId() == 0) ||
                (commentDTO.getArticleId() != 0 && commentDTO.getApartmentId() != 0)) {
            throw new IllegalStateException();
        }

    }

    @PutMapping()
    public CommentDTO update(@RequestBody CommentDTO commentDTO) {
        validateDTO(commentDTO);
        Comment comment = convertToModel(commentDTO);
        return convertToDTO(commentService.update(comment));
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<String> remove(@PathVariable(name = "commentId") long commentId) {
        boolean isRemoved = commentService.remove(commentId);
        String deletedMessage = "Comment with id: '" + commentId + "' was deleted!";
        String notDeletedMessage = "Comment with id: '" + commentId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    private Comment convertToModel(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        User owner = userService.getEntity(commentDTO.getOwnerId())
                .orElseThrow(() -> new IllegalStateException("wrong owner id"));
        comment.setOwner(owner);

        long apartmentId = commentDTO.getApartmentId();
        if (apartmentId > 0) {
            Apartment apartment = apartmentService.getEntity(commentDTO.getApartmentId())
                    .orElseThrow(() -> new IllegalStateException("wrong apartment id"));
            comment.setApartment(apartment);
        }
        long articleId = commentDTO.getArticleId();
        if (articleId > 0) {
            Article article = articleService.getEntity(commentDTO.getArticleId())
                    .orElseThrow(() -> new IllegalStateException("wrong article id"));
            comment.setArticle(article);
        }
        return comment;
    }

    private CommentDTO convertToDTO(final Comment comment) {
        final CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setOwnerId(comment.getOwner().getId());
        final Article article = comment.getArticle();
        if (article != null) {
            commentDTO.setArticleId(article.getId());
        }
        final Apartment apartment = comment.getApartment();
        if (apartment != null) {
            commentDTO.setApartmentId(apartment.getId());
        }
        return commentDTO;
    }
}
