package next.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class UpdateController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		Date createDate = new Date();
		int countOfComment = 0;
		
		Question question = new Question(questionId, writer, title, contents, createDate, countOfComment);
		questionDao.edit(question);

		ModelAndView mav = jstlView("redirect:/list.next");
		mav.addObject("questions", question);
		return mav;
	}
	

}
