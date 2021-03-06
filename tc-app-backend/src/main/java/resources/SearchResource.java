package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domains.Search;
import domains.User;
import services.SearchService;
import wrappers.SearchWrapper;

@Path("search")
@Consumes("text/plain")
@Produces("text/plain")
public class SearchResource {

	@POST
	@Path("newSearch")
	@Consumes("application/x-www-form-urlencoded")
	public boolean createNewSearch(@FormParam("userName") String userName, @FormParam("searchName") String searchName,
			@FormParam("trackterms") String[] trackterms) {
		SearchService searchService = new SearchService();
		return searchService.createNewSearch(new Search(new User(userName), searchName, trackterms));
	}

	@GET
	@Path("/allSearches/{userName}")
	public SearchWrapper getSearches(User user) {
		SearchService searchService = new SearchService();
		return new SearchWrapper(searchService.getSearches(user));
	}

	@GET
	public Search getSearch(@QueryParam("userName") String userName, @QueryParam("searchName") String searchName) {
		SearchService searchService = new SearchService();
		return searchService.getSearch(new Search(new User(userName), searchName));
	}

}
