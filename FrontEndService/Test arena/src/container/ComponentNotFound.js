import React from 'react';
import {Link} from 'react-router';


class ComponentNotFound extends React.Component{

	render(){
	return (
	      <div>
	            <h1>User Not Found !!! <Link to="/home">Login</Link> with valid Username and Password </h1>
	      </div>);
	}
}
export default ComponentNotFound;
