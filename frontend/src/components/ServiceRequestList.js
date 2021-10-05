import React, {Component} from "react";
import ReactTable from "react-table-6";
import 'react-table-6/react-table.css';
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AddServiceRequest from "./AddServiceRequest";
import EditServiceRequest from "./EditServiceRequest";
import Button from '@material-ui/core/Button';

class ServiceRequestList extends Component {

    constructor(props) {
        super(props);
        this.state = {services: []};
    }

    componentDidMount() {
        this.fetchServices();
    }

    async fetchServices() {
        fetch('/requests/services')
            .then((response) => response.json())
            .then((responseData) => {
                let fetchServices = [...responseData];
                this.setState({
                    services: fetchServices,
                });
            })
            .catch(err => console.error(err));
    }

    async remove(id) {
        if (window.confirm('Are you sure to delete?')) {
            await fetch(`/requests/services/${id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(() => {
                toast.success("Service Request deleted", {
                    position: toast.POSITION.BOTTOM_LEFT
                });
                let updatedClients = [...this.state.services].filter(i => i.id !== id);
                this.setState({services: updatedClients});
            });
        }
    }

    async addService(service) {
        await fetch('/requests/services',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(service)
            }).then(() => {
            toast.success("Service Request saved", {
                position: toast.POSITION.BOTTOM_LEFT
            });
            this.fetchServices();
        });
    }

    async updateService(service, idService) {
        await fetch('/requests/services/' + idService,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(service)
            }).then(res => {
            toast.success("Service Request updated", {
                position: toast.POSITION.BOTTOM_LEFT
            });
            this.fetchServices();
        }).catch(err => {

            }
        )
    }

    render() {
        const columns = [{
            Header: 'Id',
            accessor: 'id',
            filterable: false,
            sortable: false,
        }, {
            Header: 'Name',
            accessor: 'name',
            filterable: false,
        }, {
            Header: 'Body',
            accessor: 'body',
            filterable: false,
            sortable: false,
        }, {
            Header: 'Heading',
            accessor: 'heading',
            filterable: false,
            sortable: false,
        }, {
            Header: 'Priority',
            accessor: 'priority',
            filterable: false,
        }, {
            Header: 'Selection',
            accessor: 'selection',
            filterable: false,
            sortable: false,
        }, {
            sortable: false,
            filterable: false,
            accessor: 'id',
            Cell: ({value, row}) => (<EditServiceRequest service={row} idService={value}
                                                         updateService={this.updateService.bind(this)}/>),
            width: 100
        },
            {
                id: 'delbutton',
                sortable: false,
                filterable: false,
                width: 100,
                accessor: 'id',
                Cell: ({value}) => (<Button color="secondary"
                                            onClick={() => {
                                                this.remove(value)
                                            }}>Delete</Button>)
            }]
        return (
            <div className="App">
                <AddServiceRequest addService={this.addService.bind(this)}/>
                <ReactTable data={this.state.services} columns={columns}
                            filterable={true}/>
                <ToastContainer autoClose={1500}/>
            </div>
        );
    }
}

export default ServiceRequestList;