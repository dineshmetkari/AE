import React from 'react';
import TextField from 'material-ui/TextField';

class TFQuestion extends React.Component{
  constructor() {
      super();
      this.state = {
          question: '',
          answer: '',
      };

  }

  render(){
  return(<div>
      <TextField
        hintText="Enter the Question"
        errorText=""
        onChange={(e) => {this.handleChange(e.target.value)
                          this.sendQuestion(e.target.value)}
                  }
      /><br />
      <br / >
      <TextField
        hintText="True/False"
        errorText=""
        floatingLabelText=""
        multiLine={true}
        rows={1}
        onChange={(e) => {this.handleAnswer(e.target.value)
                          this.sendAnswer(e.target.value)}
                  }
      /> <br />
      </div>
  );
  }
handleChange(value) {
      this.setState({
          question: value
      });
}
handleAnswer(value){
  this.setState({
          answer: value
  })
}
sendQuestion(value){

  this.props.onSubmit(value)
  }
sendAnswer(value){
  this.props.getAnswer(value)
}
}
export default TFQuestion;
