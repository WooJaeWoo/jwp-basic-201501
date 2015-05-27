package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.model.Answer;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddAnswerController extends AbstractController {
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AnswerDao answerDao = new AnswerDao();
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String content = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		Long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		
		Answer answer = new Answer(writer, content, questionId);
		System.out.println(answer.getTimeFromCreateDate());
		answerDao.insert(answer);

		ModelAndView mav = jstlView("redirect:/");
		return mav;
	}

	
}
