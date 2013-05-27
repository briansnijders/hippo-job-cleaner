package nl.finalist.ecm.hippo.plugins.jcrrunner.job;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.onehippo.forge.jcrrunner.plugins.AbstractRunnerPlugin;
public class HippoJobDeleteRunner extends AbstractRunnerPlugin {

	@Override
	public void visit(final Node node) {
		try {
			if (node.isNodeType("hipposched:job")) {
				getLogger().info("Removing scheduled job on node {}",
						node.getPath());

				removeJob(node, node.getSession());
			}
		} catch (RepositoryException e) {
			getLogger().error(
					"An exception occurred while visiting a repository node.",
					e);
		}
	}

	@Override
	public void init() {
		super.init();

		getLogger().info("Running JobDeleteRunner");
	}

	@Override
	public void visitStart(final Node node) {
		super.visitStart(node);

		try {
			node.getSession().refresh(false);
		} catch (RepositoryException e) {
			getLogger().error(
					"An exception occurred while refreshing the session.", e);
		}
	}

	private void removeJob(final Node node, final Session session) throws RepositoryException {
		node.remove();
		
		session.save();
	}
}
