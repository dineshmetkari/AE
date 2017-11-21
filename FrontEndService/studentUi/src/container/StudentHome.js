import React from 'react';
import {Link} from 'react-router';
import RaisedButton from 'material-ui/RaisedButton';
import {browserHistory} from 'react-router';

export default class  StudentHome extends React.Component {
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
      <div style={userStyle}>
      <h3>Logged In as : {name}</h3>
      </div>

      <div style={linkStyle}>
      <Link href="/logout">
          <RaisedButton label="Logout" primary={true} style={buttonStyle}/>
      </Link>
      <br/>
      <h2><Link to="/register">Take Exam</Link></h2>
      <br/>

      <h2><Link to={`results/name`}>Results</Link></h2>
      <br/>
      <h2><Link to="/register">Update Profile</Link></h2>
      <br/>
      </div>
      </div>
    )
  }
}

const linkStyle = {
  margin: 8,
 marginLeft: "43%"
};

const buttonStyle = {
  marginTop : 8,
  position:'relative',
  marginLeft: "85%"
};

const userStyle = {
  position: 'absolute',
  display : 'inline',
  marginLeft : 25
}
