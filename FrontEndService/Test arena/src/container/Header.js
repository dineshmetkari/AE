import React from 'react';
import {AppBar, Tabs, Tab} from 'material-ui';
import {Link} from 'react-router';
import RaisedButton from 'material-ui/RaisedButton';


const style = {
  margin: 14,
};

export default class Header extends React.Component{
	render() {
		return(
			 <AppBar title="Exam Service"
			 	iconClassNameRight="muidocs-icon-navigation-expand-more">
			 	<RaisedButton label="Create Exam" secondary={true} containerElement = {<Link to = '/' />} style={style} />	
			 </AppBar>
			);
	}
}
