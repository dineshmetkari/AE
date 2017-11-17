package com.stackroute.assessmentengine.engineService.domain;
public class Display {
	private String question;
	private String option;
private String msg;
    public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

	public Display() {
    }
	public Display(String m) {
		this.msg=m;
	}

    public Display(String question,String options) {
        this.question=question;
        this.option=options;
    }

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

    

}
