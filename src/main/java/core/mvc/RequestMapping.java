package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AddAnswerController;
import next.controller.DelAnswerController;
import next.controller.EditQuestionController;
import next.controller.ListController;
import next.controller.MListController;
import next.controller.SaveController;
import next.controller.ShowController;
import next.controller.UpdateController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/updateForm.next", new EditQuestionController());
		mappings.put("/editSave.next", new UpdateController());
		mappings.put("/api/list.next", new MListController());
		mappings.put("/api/addanswer.next", new AddAnswerController());
		mappings.put("/api/delanswer.next", new DelAnswerController());
		mappings.put("/save.next", new SaveController());
		mappings.put("/form.next", new ForwardController("form.jsp"));
		
		
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
