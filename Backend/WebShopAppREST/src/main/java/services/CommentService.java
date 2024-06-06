package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Comment;
import dao.CommentDAO;

@Path("/comments")
public class CommentService {
	@Context
	ServletContext ctx;
	    
	public CommentService() {}
	    
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("commentDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("commentDAO", new CommentDAO(contextPath));
		}
	}
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comment> getCommentsByFactoryId(@PathParam("id") String id) {
		CommentDAO dao = (CommentDAO) ctx.getAttribute("commentDAO");
        return dao.findCommentsByFactoryId(id);
    }
}
