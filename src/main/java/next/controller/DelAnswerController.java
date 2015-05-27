package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DelAnswerController extends AbstractController {
	AnswerDao answerDao = new AnswerDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		
		answerDao.remove(questionId, writer);
		ModelAndView mav = jstlView("redirect:/");
		return mav;
	}
}
