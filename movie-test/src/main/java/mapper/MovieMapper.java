package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.movie.model.vo.Movie;

@Mapper
public interface MovieMapper {

	void addMovie(Movie vo);
	List<Movie> movieList();
}