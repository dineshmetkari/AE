import React from 'react';
import SearchQ from '../presentation/SearchQ';
import BluePrint from '../presentation/BluePrint';

export default class Main extends React.Component
{
	constructor(props)
	   {
		super(props);
		this.state={paperjson:[]};		
	}
	render = () => 
	{
		if(this.state.paperjson==0)
		{
		return(
			<div>
				<SearchQ questionData={this.preview} />
				</div>
				);
		}
		else
		{
			return(
				<div>
				<BluePrint vars={this.state.paperjson} />
			</div>
			);
		}		
	}
	// this is a callback function...
	preview = (x) => 
	{
		this.setState({paperjson:x});
		console.log(this.state.dems+"mainaaaaa");
	}
}
