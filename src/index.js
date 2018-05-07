import $ from 'jquery';


const React = require('react');
const ReactDOM = require('react-dom');


class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {factories: []};

        this.showFactoryForm = this.showFactoryForm.bind(this);
        this.hideFactoryForm = this.hideFactoryForm.bind(this);
        this.factoryFormSubmit = this.factoryFormSubmit.bind(this);
        this.hideChildForm = this.hideChildForm.bind(this);
        this.showChildForm = this.showChildForm.bind(this);
        this.childFormSubmit = this.childFormSubmit.bind(this);
        this.addFactory = this.addFactory.bind(this);
        this.addChildren = this.addChildren.bind(this);
        this.showDeleteForm = this.showDeleteForm.bind(this);
        this.hideDeleteForm = this.hideDeleteForm.bind(this);
        this.deleteFormSubmit = this.deleteFormSubmit.bind(this);
        this.deleteFactory = this.deleteFactory.bind(this);
    }

    loadTreeFromServer() {
        var self = this;
        $.ajax({
          url: "numbertreereact.herokuapp.com/api"
        }).then(function (data) {
          console.log(data);
          self.setState({factories: data.factories});
        });
      }


    componentDidMount() {
        this.loadTreeFromServer();
        this.hideChildForm();
        this.hideFactoryForm();
        this.hideDeleteForm();
    }

    componentWillReceiveProps(nextProps, nextState) {
        if (this.state.data !== nextState.data) {
            this.setState(nextProps);
        }
    }

    showFactoryForm() {
        document.getElementById("addFactoryForm").style.display = '';
    }

    hideFactoryForm() {
        document.getElementById("addFactoryForm").style.display = "none";
    }

    showChildForm() {
        document.getElementById("addChildrenForm").style.display = '';
    }

    hideChildForm() {
        document.getElementById("addChildrenForm").style.display = "none";
    }

    showDeleteForm() {
        document.getElementById("deleteFactoryForm").style.display = '';
    }

    hideDeleteForm() {
            document.getElementById("deleteFactoryForm").style.display = "none";
    }

    factoryFormSubmit() {
        var form = document.getElementById("addFactoryForm");
        var name = form.elements["factory_name"].value;
        var rangeLow = form.elements["range_low"].value;
        var rangeHigh = form.elements["range_high"].value;
        this.hideFactoryForm();
        document.getElementById("addFactoryForm").reset();
        this.addFactory(name, rangeLow, rangeHigh);
    }

    childFormSubmit() {
        var form = document.getElementById("addChildrenForm");
        var factoryId = form.elements["factory_Id"].value;
        var numberOfChildren = form.elements["number_of_children"].value;
        this.hideChildForm();
        document.getElementById("addChildrenForm").reset();
        this.addChildren(factoryId, numberOfChildren);
    }

    deleteFormSubmit() {
        var form = document.getElementById("deleteFactoryForm");
        var factoryId = form.elements["factory_Id"].value;
        this.hideFactoryForm();
        document.getElementById("deleteFactoryForm").reset();
        this.deleteFactory(factoryId);
    }

    addFactory(name, rangeLow, rangeHigh) {
        var self = this;
            $.ajax({
            method: "POST",
              url: "/api/create-factory",
              data: {name: name, rangeLow: rangeLow, rangeHigh: rangeHigh}
            }).then(function () {
              self.loadTreeFromServer();
              console.log("Factory added.");
            });
    }

    addChildren(factoryId, numberOfChildren) {
        var self = this;
            $.ajax({
                method: "POST",
                  url: "/api/create-children",
                  data: {factoryId: factoryId, numberOfChildren: numberOfChildren}
                }).then(function () {
                  self.loadTreeFromServer();
                  console.log("Children added.");
                });
    }

    deleteFactory(factoryId) {
            var self = this;
                $.ajax({
                    method: "DELETE",
                      url: "/api/delete-factory/" + factoryId,
                    }).then(function () {
                      self.loadTreeFromServer();
                      console.log("Factory deleted.");
                    });
        }

    render() {

        return(
        <div>
            <div id="addFactory">
                <span> <input type="button" className="button" value="Add Factory" onClick={this.showFactoryForm} /> </span>
                <span> <input type="button" className="button" value="Add Children" onClick={this.showChildForm} /> </span>
                <span> <input type="button" className="button" value="Delete Factory" onClick={this.showDeleteForm} /> </span>
                <form id="addFactoryForm">
                    <span>Name:  <input type="text" name="factory_name" id="factory_name" /></span>
                    <span>Range:
                                <input type="number" name="range_low" id="range_low" />
                                <input type="number" name="range_high" id="range_high" />
                            </span>
                    <input type="button" value="Add" onClick={this.factoryFormSubmit} />
                    <input type="reset" value="Cancel" onClick={this.hideFactoryForm} />
                </form>
                <form id="addChildrenForm">
                    <span>Factory ID:  <input type="number" name="factory_Id" id="factory_Id" /></span>
                    <span>Number of Children: <input type="number" name="number_of_children" id="number_of_children" /></span>
                     <input type="button" value="Generate" onClick={this.childFormSubmit} />
                     <input type="reset"  value="Cancel" onClick={this.hideChildForm} />
                </form>
                <form id="deleteFactoryForm">
                    <span>Factory Id: <input type="number" name="factory_Id" id="factory_Id" /></span>
                    <input type="button" value="Delete" onClick={this.deleteFormSubmit} />
                    <input type="reset"  value="Cancel" onClick={this.hideDeleteForm} />
                </form>
            </div>
            <FactoryList factories={this.state.factories}/>
        </div>
        )
    }
}

class FactoryList extends React.Component {
    render() {
        var factories = this.props.factories.map(factory =>
            <Factory key={factory.id} factory={factory}/>
        );
        return(
            <ul className="tree">
                <li>{factories}</li>
            </ul>
        )
    }
}


class Factory extends React.Component {

    render() {

    var childNodes = this.props.factory.childNodes.map(childNode =>
            <p key={childNode.id} childnode={childNode}>{childNode.number}</p>
            );
        return(
            <ul className="factory">
                <li>ID: {this.props.factory.id}</li>
                <li>Name: {this.props.factory.name}</li>
                <li>{this.props.factory.rangeLow} : {this.props.factory.rangeHigh}</li>
                <ul className="children">
                    <li>{childNodes}</li>
                </ul>
            </ul>
        )
    }
}


ReactDOM.render(
    <App />,
    document.getElementById('root')
)