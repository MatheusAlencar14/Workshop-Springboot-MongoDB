package com.devjava.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.devjava.workshopmongo.domain.Post;
import com.devjava.workshopmongo.domain.User;
import com.devjava.workshopmongo.dto.AuthorDTO;
import com.devjava.workshopmongo.dto.CommentDTO;
import com.devjava.workshopmongo.repository.PostRepository;
import com.devjava.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");		
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2023"), "Partiu viagem", "Vou viajar para Natal!", new AuthorDTO(alex));
		Post post2 = new Post(null, sdf.parse("23/03/2023"), "Buenos días", "Hoy me quedaré em mi casa", new AuthorDTO(alex));
		
		CommentDTO comment1 = new CommentDTO("Boa viagem, parceiro!", sdf.parse("21/03/2023"), new AuthorDTO(bob));
		CommentDTO comment2 = new CommentDTO("Aproveita lá", sdf.parse("21/03/2023"), new AuthorDTO(maria));
		CommentDTO comment3 = new CommentDTO("Tenga un bueníssimo día", sdf.parse("23/03/2023"), new AuthorDTO(bob));

		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().add(comment3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		alex.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(alex);
	}

}
