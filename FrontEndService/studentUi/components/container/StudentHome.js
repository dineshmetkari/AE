import React from 'react';
import {Link} from 'react-router';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';
import ActionHome from 'material-ui/svg-icons/action/home';

export default class  StudentHome extends React.Component {

  render(){
    return(
      <div style={{display:'flex', flexDirection:'row', justifyContent:'space-around', marginTop:'20%'}}>

    <IconButton tooltip="SVG Icon">
      <ActionHome />
</IconButton>
         <div style={{padding:'0px 50px', backgroundColor:'rgb(0, 188, 212)', border:'2px solid black', borderRadius:'10px'}}>

          <h3><Link to="/register" style={{textDecoration:'none',color:'black'}}>Apply for Exam</Link></h3>

        </div>

        <div style={{padding:'0px 50px', backgroundColor:'rgb(0, 188, 212)', border:'2px solid black', borderRadius:'10px'}}>
          <h3><Link to="/register" style={{textDecoration:'none',color:'black'}}>View Results</Link></h3>
        </div>

        <div style={{padding:'0px 50px',backgroundColor:'rgb(0, 188, 212)', border:'2px solid black', borderRadius:'10px'}}>
          <h3><Link to="/register" style={{textDecoration:'none',color:'black'}}>Update Profile</Link></h3>
        </div>
      </div>
    )
  }
}
const style = {
  margin: 8,
 marginLeft: 600
};
