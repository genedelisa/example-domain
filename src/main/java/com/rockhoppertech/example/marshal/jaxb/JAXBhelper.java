package com.rockhoppertech.example.marshal.jaxb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rockhoppertech.example.domain.Roles.Role;
import com.rockhoppertech.example.domain.User;

/**
 * Just to see how our User will be marshaled if used in a web service.
 * 
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@Service
public class JAXBhelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {

		User user = new User("bozo", "password", "bozo", "clown", "foo@bar.com");
		user.addRole(Role.user);

		JAXBhelper helper = new JAXBhelper();
		helper.xmlToStdout(user);

		helper.saveXML(user, "user.xml");
		User read = helper.readUserXML("user.xml");
		System.out.println(read);
		
		UserList list = new UserList();
		list.add(user).add(new User("quux", "password", "John", "D'oh", "foo@baz.com"));
		helper.xmlToStdout(list);
	}

	public JAXBhelper() {
	}

	public void xmlToStdout(UserList userList) {
		try {
			JAXBContext jaxbc = JAXBContext.newInstance(UserList.class);
			Marshaller marshaller = jaxbc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(userList, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
			this.logger.error(e.getMessage());
		}
	}
	public void xmlToStdout(User user) {
		try {
			JAXBContext jaxbc = JAXBContext.newInstance(User.class);
			Marshaller marshaller = jaxbc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// StringWriter sw = new StringWriter();
			// marshaller.marshal(taskList, sw);
			// this.logger.debug(sw.toString());
			marshaller.marshal(user, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
			this.logger.error(e.getMessage());
		}
	}

	public void saveXML(User user, String fileName) {
		try {
			JAXBContext jaxbc = JAXBContext.newInstance(User.class);
			Marshaller marshaller = jaxbc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			marshaller.marshal(user, sw);
			this.logger.debug(sw.toString());
			OutputStream out = null;
			try {
				out = new FileOutputStream(fileName);
				marshaller.marshal(user, out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
			this.logger.error(e.getMessage());
		}
	}

	public User readUserXML(String fileName) {
		User user = null;
		try {
			JAXBContext jaxbc = JAXBContext.newInstance(User.class);
			Unmarshaller marshaller = jaxbc.createUnmarshaller();
			InputStream file = null;
			try {
				file = new FileInputStream(fileName);
				user = (User) marshaller.unmarshal(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				this.logger.error(e.getMessage());
			} finally {
				try {
					file.close();
				} catch (IOException e) {
				}
			}

		} catch (JAXBException e) {
			e.printStackTrace();
			this.logger.error(e.getMessage());
		}
		return user;
	}

}
