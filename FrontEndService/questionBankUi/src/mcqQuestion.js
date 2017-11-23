import React from 'react';
import TextField from 'material-ui/TextField';
const styles = {
  customWidth: {
    width: 400,
  },
  padding:{
    paddingLeft:20,
  },

};
class McqQuestion extends React.Component{
  constructor(){
    super()
    this.state= {
                  question: '',
                  optionA: '',
                  optionB: '',
                  optionC: '',
                  optionD: '',
                  answer: ''
                }
  }
  render(){
    return(
  <div>
  <br />
  <br />
  <TextField
        hintText="Type your Question here"
        fullWidth={true}
        multiLine={true}
        rows={2}
        rowsMax={4}
        style={styles.padding}
        onChange={(e) => {this.handleQuestion(e.target.value)
                          this.sendQuestion(e.target.value)}
                  }
      /><br />
  <TextField
          floatingLabelText="option A"
          floatingLabelFixed={true}
          style={styles.padding}
          onChange={(e) => {this.handleOptionA(e.target.value)
                            this.sendOptionA(e.target.value)}
                  }
  /><br /><br />
  <TextField
          floatingLabelText="option B"
          floatingLabelFixed={true}
          style={styles.padding}
          onChange={(e) => {this.handleOptionB(e.target.value)
                            this.sendOptionB(e.target.value)}
                  }
  /><br /> <br />
  <TextField
          floatingLabelText="option C"
          floatingLabelFixed={true}
          style={styles.padding}
          onChange={(e) => {this.handleOptionC(e.target.value)
                            this.sendOptionC(e.target.value)}
                  }
  /><br /><br />
  <TextField
          hintText=""
          floatingLabelText="option D "
          floatingLabelFixed={true}
          style={styles.padding}
          onChange={(e) => {this.handleOptionD(e.target.value)
                            this.sendOptionD(e.target.value)}
                  }
  /><br /><br />
  <TextField
          hintText="A or B or C or D"
          floatingLabelText="Correct Option"
          floatingLabelFixed={true}
          style={styles.padding}
          onChange={(e) => {this.handleAnswer(e.target.value)
                            this.sendAnswer(e.target.value)}
                    }
  /><br /><br />
  </div>
  );
  }
  handleQuestion(value) {
        this.setState({
            question: value
        });
  }
  handleOptionA(value) {
        this.setState({
            optionA: value
        });
  }
  handleOptionB(value) {
        this.setState({
            optionB: value
        });
  }
  handleOptionC(value) {
        this.setState({
            optionC: value
        });
  }
  handleOptionD(value) {
        this.setState({
            optionD: value
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
  sendOptionA(value){
      this.props.optionA(value)
      }
  sendOptionB(value){
      this.props.optionB(value)
    }
  sendOptionC(value){
      this.props.optionC(value)
    }
  sendOptionD(value){
      this.props.optionD(value)
    }
  sendAnswer(value){
    this.props.getAnswer(value)
  }
}
export default McqQuestion;
