package com.stackroute.assessmentengine.rediscache.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stackroute.assessmentengine.rediscache.domain.QuestionBean;
import com.stackroute.assessmentengine.rediscache.exceptions.RedisServerDownException;


@RunWith(MockitoJUnitRunner.class)
public class QuestionRepositoryTest {
	@Mock
	QuestionRepository questionRepository;
	@Mock
	QuestionBean question;
	
	long l=122222222L;

	@Test
	public void getquestionTest() throws RedisServerDownException  {
		Mockito.doReturn(question).when(questionRepository).getquestion("1","abc@gmail.com");
		QuestionBean q=questionRepository.getquestion("1","abc@gmail.com");
		assertThat(q, is(equalTo(question)));
			}
	@Test
	public void addquestionTest() throws RedisServerDownException {
		Mockito.doReturn(question).when(questionRepository).addquestion(question,"abc@gmail.com");
		QuestionBean q=questionRepository.addquestion(question,"abc@gmail.com");
		assertThat(q, is(equalTo(question)));
		
	}
	@Test
	public void deleteQuestionTest() throws RedisServerDownException
	{
		
		Mockito.doReturn(l).when(questionRepository).deletequestions("1","abc@gmail.com");
		long l1=questionRepository.deletequestions("1","abc@gmail.com");
		assertThat(l1, is(equalTo(l)));
	}
}
