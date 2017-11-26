import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import QuestionLayout from './questionLayout';
import {Card, CardHeader} from 'material-ui/Card';
import TextField from 'material-ui/TextField';

const styles = {
  customWidth:{
    width: 151
  },
  textbox:{
    width: 150,
    position:'relative',
    margin: 'auto',
    marginRight:-1
  },
  cardWidth :{
    margin: "auto",
    marginTop:100,
    width: 650,
    height: 350,
    padding: 5,
    },
    cardHeader:{
      marginLeft: 170,
    },
  buttonAlign:{
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 280,
    marginTop:0,
    position: 'absolute',
    },
    ul:{
      paddingLeft: 9,
      display: "inline-block",
      textAlign: "center"
    }
};
class QuestionProperties extends React.Component {
constructor(props){
  super(props);
  this.state= {
                typeValue: 1,
                levelValue: 1,
                domainValue: 1,
                complexityValue: 1,
                topicValue: 1,
                markValue: 1,
                mcq: false,
                tf : false,
                name: []
              }
  this.openLayout=this.openLayout.bind(this);
  this.closeLayout = this.closeLayout.bind(this);
  }
  render() {
    return (
      <div className="QuestionProperties" >

      <br/>
      <Card style={styles.cardWidth} >
        <CardHeader
          title="Enter the Question Details Below"
          style={styles.cardHeader}
        />
        <ul style= {styles.ul}>
        <li style= {styles.ul}>
          <DropDownMenu value={this.state.domainValue} onChange={this.handleDomainChange}  style={styles.customWidth}>
            <MenuItem value={1} primaryText="Java" />
            <MenuItem value={2} primaryText="Python" />
            <MenuItem value={3} primaryText="Database" />
          </DropDownMenu>
        </li>
        <li style= {styles.ul}>
          <DropDownMenu value={this.state.topicValue} onChange={this.handleTopicChange}  style={styles.customWidth}>
            <MenuItem value={1} primaryText="Strings" />
            <MenuItem value={2} primaryText="collections" />
          </DropDownMenu>
        </li>
        <li style= {styles.ul}>
          <DropDownMenu value={this.state.typeValue} onChange={this.handleTypeChange}  style={styles.customWidth}>
            <MenuItem value={1} primaryText="MCQ" />
            <MenuItem value={2} primaryText="T/F" />
          </DropDownMenu>
        </li>
        <li style= {styles.ul}>
          <DropDownMenu value={this.state.complexityValue} onChange={this.handleComplexityChange}  style={styles.customWidth}>
            <MenuItem value={1} primaryText="Easy" />
            <MenuItem value={2} primaryText="Medium" />
            <MenuItem value={3} primaryText="Hard" />
          </DropDownMenu>
        </li>
        <li style= {styles.ul}>
        <DropDownMenu value={this.state.levelValue} onChange={this.handleLevelChange}  style={styles.customWidth}>
          <MenuItem value={1} primaryText="level 1" />
          <MenuItem value={2} primaryText="level 2" />
          <MenuItem value={3} primaryText="level 3" />
        </DropDownMenu>
        </li>
        <li style= {styles.ul}>
        <DropDownMenu value={this.state.markValue} onChange={this.handleMarkChange}  style={styles.customWidth}>
          <MenuItem value={1} primaryText="1 mark" />
          <MenuItem value={2} primaryText="2 marks" />
          <MenuItem value={3} primaryText="3 marks" />
          <MenuItem value={3} primaryText="4 marks" />
        </DropDownMenu>
        </li>
        <li style= {styles.ul}>
          <TextField
            hintText="name"
            onChange = {this.handleUpdateInput}
            style= {styles.textbox}
          />
        </li>
        </ul>
      <div>
        <br/>
        <RaisedButton
          label = "Create"
          onClick = {this.openLayout}
          style = {styles.buttonAlign}
          primary={true}
        />
          < QuestionLayout openMcq={this.state.mcq} openTf={this.state.tf}
                            type = {this.state.typeValue} level = {this.state.levelValue}
                            domain = {this.state.domainValue} complexity = {this.state.complexityValue}
                            topic = {this.state.topicValue}
                            setDefault = {this.closeLayout} marks = {this.state.markValue}
          />
        </div>
      </Card>
      </div>
    );
  }
  handleDomainChange = (event, index, domainValue) => this.setState({domainValue});
  handleTopicChange = (event, index, topicValue) => this.setState({topicValue});
  handleTypeChange = (event, index, typeValue) => this.setState({typeValue});
  handleComplexityChange = (event, index, complexityValue) => this.setState({complexityValue});
  handleLevelChange = (event, index, levelValue) => this.setState({levelValue});
  handleMarkChange = (event, index, markValue) => this.setState({markValue});

    openLayout(){
      if(this.state.typeValue === 1){
        this.setState({mcq: true});
        this.setState({tf: false});
      }
      else if(this.state.typeValue === 2){
        this.setState({mcq: false});
        this.setState({tf: true});
      }
      }
      closeLayout(){
        this.setState({mcq: false});
        this.setState({tf: false});
      }
      handleUpdateInput(value){
          this.setState({
            name: [value],
          });
        };
        componentDidMount(){

        }



}
export default QuestionProperties;
