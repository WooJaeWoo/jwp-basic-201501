package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class SaveController extends AbstractController {
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		
		Question question = new Question(writer, title, contents);
		questionDao.insert(question);
		
		ModelAndView mav = jstlView("redirect:/list.next");
		return mav;
	}	
}
