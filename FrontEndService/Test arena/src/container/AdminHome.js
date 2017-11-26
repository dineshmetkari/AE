import React, {Component} from 'react';
import './AdminHome.css';
import App from '../addquestionscomponent/App';
import Main2 from './Main2';
import Paper from 'material-ui/Paper';

const userStyle={
width:'100vw',
height:'100vh',
display:'flex',
flexDirection:'row',
};

// const sideNav={
//   height:'100vh',
//   width:'18vw',
//   backgroundColor:'#F8F8F8',
//   display:'flex',
//   flexDirection:'column',
//   alignItems:'center',
//   alignContents:'center'
// }

export default class AdminHome extends Component {
  constructor(props){
    super(props);

    this.state={
    renderContent:''
    }
    this.renderAddQuestion=this.renderAddQuestion.bind(this);
    this.renderQuestionPapers=this.renderQuestionPapers.bind(this);
   }
   renderAddQuestion(){
    this.setState({renderContent:<App />});
   }
   renderQuestionPapers(){
    this.setState({renderContent:<Main2 />});
   }

 render(){
   var name = this.props.location.state;

   console.log(name);
   return(
     <div style={userStyle}>
      <div style={{marginRight:'5vw'}}>
            <Paper zDepth={1}>
            <div className="sideNav">
                <div onClick={this.renderAddQuestion}>
                <p>Add questions</p>
                </div>
                <div onClick={this.renderQuestionPapers}>
                <p>Question papers</p>
                </div>
            </div>
            </Paper>
      </div>
              <div className="component">
              {this.state.renderContent}
              </div>
      </div>
   );
 }
}
