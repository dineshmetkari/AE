import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton';


const divStyle={
 margin:'12px'
};

export default class AdminHome extends Component {
  constructor(props){
    super(props);

    this.state={
    name : '',
    }
   }
 render(){
   var name = this.props.location.state;
   console.log(name);
   return(
     <div>


     <div id ="Container">
         <div style={userStyle}>
         <h3>Logged In as : {name}</h3>
         </div>
         <div id ="buttons">
             <RaisedButton label="Add Questions" primary={true} style={divStyle} />
             <RaisedButton label="Question Paper" primary={true} style={divStyle} />
             <RaisedButton label="Evaluation" primary={true} style={divStyle} />
             <RaisedButton label="Update Profile" primary={true} style={divStyle} />
         </div>
         </div>
     </div>
   );
 }
}
const userStyle = {
  position: 'absolute',
  display : 'inline',
  marginLeft : 1200
}
