package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DelAnswerController extends AbstractController {
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AnswerDao answerDao = new AnswerDao();
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		
		answerDao.remove(questionId, writer);
		ModelAndView mav = jstlView("redirect:/");
		return mav;
	}
}
