package com.roi.goliath.device;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class DeviceBean {

    //private static final Logger LOG = LoggerFactory.getLogger
    //(DeviceBean.class);

    @PersistenceContext
    private EntityManager em;

    @Inject
    private DeviceLibraryProxyProvider deviceLibraryProxyProvider;

    public Device findDeviceById(String id) {
        Device device = em.find(Device.class, id);
        if (device == null) {
            throw new DeviceNotFoundException(String
                    .format("Device with id [%s] does not exist", id));
        }
        return device;
    }

    public void executeCommand(DeviceScheduleDto deviceCommandDto) {
        //LOG.debug("Executing command!");
        // get device from the DB
        Device device = this.findDeviceById(deviceCommandDto.getActuatorId());
        // obtain library proxy
        DeviceLibraryProxy libraryProxy = deviceLibraryProxyProvider
                .findLibraryByName(device.getName());
        // run each command on library proxy instance
        libraryProxy.executeCommands(deviceCommandDto.getCommandsToExecute());
    }
}
