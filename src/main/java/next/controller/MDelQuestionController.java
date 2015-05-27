package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class MDelQuestionController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		Question question = questionDao.findById(questionId);
		
		Result result = okToDelete(question.getQuestionId(), question.getCountOfComment());
		if (result.isStatus())
			questionDao.remove(questionId);
		
		ModelAndView mav = jsonView();
		mav.addObject("result", result);
		return mav;
	}

	private Result okToDelete(long questionId, int countOfComment) {
		if (countOfComment == 0){
			return Result.ok();
		}
		else if (!checkExistWriter(questionId)) {
			return Result.fail("Comment Exists!");
		}
		return Result.ok(); 
	}
	
	private boolean checkExistWriter(long questionId) {
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();
		String questionWriter = questionDao.findById(questionId).getWriter();
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		for (int i = 0; i < answers.size(); i++) {
			String answerWriter = answers.get(i).getWriter();
			System.out.println(answerWriter);
			if (!answerWriter.equals(questionWriter))
				return false;
		}
		return true;
	}
}
