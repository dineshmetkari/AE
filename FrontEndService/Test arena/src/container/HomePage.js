import React from 'react';
import {Link} from 'react-router';
import Paper from 'material-ui/Paper';
import AdminLogo from './ic_business_center_black_48px.svg';
import StudentLogo from './ic_school_black_48px.svg';

const style = {
display:'flex',
flexDirection:'row',
alignItems:'center',
alignSelf:'center',
justifyContent:'space-around',
height:'80vh',
width:'100vw',
};

export default class Login extends React.Component {
constructor(props){
  super(props);
  this.state={

  }
 }

render() {
    return (

          <div style={style}>
            <Link to="/adminlogin">
              <Paper zDepth={3}>
                <div style={{height:'10vh', width:'20vw', border:'2px solid black', backgroundColor:'#00bcd4',display:'flex', flexDirection:'row',alignItems:'center',alignSelf:'center',justifyContent:'center',}}>
                  <img src={AdminLogo} alt="Admin Logo" />
                  <h2 style={{marginLeft:'10px'}}>Admin login</h2>
                </div>
              </Paper>
            </Link>
            <Link to="/studentLogin">
              <Paper zDepth={3}>
                <div style={{height:'10vh', width:'20vw', border:'2px solid black', backgroundColor:'#00bcd4',display:'flex', flexDirection:'row',alignItems:'center',alignSelf:'center',justifyContent:'center'}}>
                  <img src={StudentLogo} alt="Student Logo" />
                  <h2 style={{marginLeft:'10px'}}>Student login</h2>
                </div>
              </Paper>
            </Link>
          </div>
    );
  }
}
