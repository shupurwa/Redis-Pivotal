package com.purwar.demo.SpringPersontest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.purwar.demo.SpringPersontest.domain.Person;
import com.purwar.demo.SpringPersontest.domain.repository.PersonRepository;

@Configuration
public class RedisConfig {
	@Bean
	public PersonRepository repository(RedisTemplate<String, Person> redisTemplate) {
		return new PersonRepository(redisTemplate);
	}

	@Bean
	public RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Person> template = new RedisTemplate();

		template.setConnectionFactory(redisConnectionFactory);

		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<Person> personSerializer = new Jackson2JsonRedisSerializer<>(Person.class);

		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(personSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(personSerializer);

		return template;
	}

}
