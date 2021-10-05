import React, {useState} from 'react';
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";

const AddServiceRequest = (props) => {
    const [open, setOpen] = useState(false);
    const [service, setservice] = useState({
        name: '', body: '', heading: '', priority: '', selection: 0
    });

    // Open the modal form
    const handleClickOpen = () => {
        setOpen(true);
        setservice({name: '', body: '', heading: '', priority: '', selection: 0});
    };

    // Close the modal form
    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event) => {
        setservice({...service, [event.target.name]: event.target.value});
    };

    // Save car and close modal form
    const handleSave = () => {
        props.addService(service);
        handleClose();
    };

    const convertSelection = (value) => {
        switch (value) {
            case 'Laptop':
                return 0;
            case 'PC':
                return 1;
            case 'Monitor':
                return 2;
            default:
                return value;
        }
    };

    return (
        <div>
            <Button style={{margin: 10}} onClick={handleClickOpen}>New
                Service Request
            </Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New Service Request</DialogTitle>
                <DialogContent>
                    <TextField autoFocus fullWidth type="text" placeholder="Name" name="name"
                               value={service.name} onChange={handleChange}/><br/>
                    <TextField autoFocus fullWidth type="text" placeholder="Body" name="body"
                               value={service.body} onChange={handleChange}/><br/>
                    <TextField autoFocus fullWidth type="text" placeholder="Heading" name="heading"
                               value={service.heading} onChange={handleChange}/><br/>
                    <TextField autoFocus fullWidth type="number" placeholder="Priority" name="priority"
                               value={service.priority} onChange={handleChange}/><br/>
                    <Select
                        name="selection"
                        value={convertSelection(service.selection)}
                        autoFocus
                        fullWidth
                        onChange={handleChange}
                    >
                        <MenuItem value="0">Laptop</MenuItem>
                        <MenuItem value="1">PC</MenuItem>
                        <MenuItem value="2">Monitor</MenuItem>
                    </Select>
                </DialogContent>
                <DialogActions>
                    <Button color="secondary" onClick={handleClose}>Cancel</Button>
                    <Button color="primary" onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};
export default AddServiceRequest;