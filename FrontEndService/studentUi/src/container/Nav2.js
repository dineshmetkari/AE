import React from 'react';
import ReactDom from 'react-dom';
import AppBar from 'material-ui/AppBar';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import {Link} from 'react-router';
import RaisedButton from 'material-ui/RaisedButton';
import {browserHistory} from 'react-router';

const rightButtons = (
    <div className="appBarButton">
        <Link href="/logout">
            <RaisedButton label="Logout" style={buttonStyle}/>
        </Link>
    </div>
);

const buttonStyle = {
    color: 'white'
}

export default class Nav extends React.Component{
	render(){
		return(
			<div>
			<MuiThemeProvider>
			<div>
                     <AppBar
                        title="TestArena"
                        iconElementRight={rightButtons}
                      />
                    </div>
					 </MuiThemeProvider>
		      </div>
			);
	}
}
