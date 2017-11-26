package sample.spring.chapter16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import sample.spring.chapter16.dao.FixedDepositDao;
import sample.spring.chapter16.domain.FixedDepositDetails;

@Service
public class FixedDepositServiceImpl implements FixedDepositService {
	@Autowired
	private FixedDepositDao fixedDepositDao;

	@Autowired
	private MutableAclService mutableAclService;

	private void addPermission(long fixedDepositId, Sid recipient,
			Permission permission) {
		MutableAcl acl;
		ObjectIdentity oid = new ObjectIdentityImpl(FixedDepositDetails.class,
				fixedDepositId);

		try {
			acl = (MutableAcl) mutableAclService.readAclById(oid);
		} catch (NotFoundException nfe) {
			acl = mutableAclService.createAcl(oid);
		}

		acl.insertAce(acl.getEntries().size(), permission, recipient, true);
		mutableAclService.updateAcl(acl);

		System.out.println("Added permission " + permission + " for Sid "
				+ recipient + " fixedDepositId " + fixedDepositId);
	}

	@Override
	public List<FixedDepositDetails> getFixedDeposits(String user) {
		return fixedDepositDao.getFixedDeposits(user);
	}

	@Override
	public List<FixedDepositDetails> getAllFixedDeposits() {
		return fixedDepositDao.getAllFixedDeposits();
	}

	@Override
	public void saveFixedDeposit(FixedDepositDetails fixedDepositDetails) {
		fixedDepositDao.saveFixedDeposit(fixedDepositDetails);
		addPermission(fixedDepositDetails.getId(), new PrincipalSid(
				SecurityContextHolder.getContext().getAuthentication()
						.getName()), BasePermission.READ);
		addPermission(fixedDepositDetails.getId(), new PrincipalSid(
				SecurityContextHolder.getContext().getAuthentication()
						.getName()), BasePermission.WRITE);
	}

	@Override
	public void closeFixedDeposit(int fixedDepositId) {
		fixedDepositDao.closeFixedDeposit(fixedDepositId);
		ObjectIdentity oid = new ObjectIdentityImpl(FixedDepositDetails.class,
				fixedDepositId);
		mutableAclService.deleteAcl(oid, false);
	}

	@Override
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		return fixedDepositDao.getFixedDeposit(fixedDepositId);
	}

	@Override
	public void editFixedDeposit(FixedDepositDetails fixedDepositDetails) {
		fixedDepositDao.editFixedDeposit(fixedDepositDetails);
	}

	@Override
	public void provideAccessToAdmin(int fixedDepositId) {
		addPermission(fixedDepositId, new PrincipalSid("admin"),
				BasePermission.READ);
		addPermission(fixedDepositId, new PrincipalSid("admin"),
				BasePermission.ADMINISTRATION);
		addPermission(fixedDepositId, new PrincipalSid("admin"),
				BasePermission.DELETE);
	}
}
