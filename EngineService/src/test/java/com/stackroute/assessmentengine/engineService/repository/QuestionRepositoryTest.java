package com.stackroute.assessmentengine.engineService.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stackroute.assessmentengine.engineService.domain.Question;
import com.stackroute.assessmentengine.engineService.exceptions.ResourceNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class QuestionRepositoryTest {
	@Mock
	QuestionRepository questionRepository;
	@Mock
	Question question;

	@Test
	public void getquestionTest() throws ResourceNotFoundException {
		Mockito.doReturn(question).when(questionRepository).getquestion(question);
		Question q=questionRepository.getquestion(question);
		assertThat(q, is(equalTo(question)));
	}

}
