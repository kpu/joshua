package joshua.decoder.ff;

import java.util.List;

import joshua.decoder.chart_parser.SourcePath;
import joshua.decoder.ff.state_maintenance.DPState;
import joshua.decoder.ff.tm.Rule;
import joshua.decoder.hypergraph.HGNode;
import joshua.decoder.hypergraph.HyperEdge;

/**
 * Implements stateless feature functions.  Stateless feature functions do not need any access to
 * the state, so they are computable as soon as they are seen.  The typical use case for stateless
 * feature function is for feature that are found in the grammar file.  If the weight vector is
 * known, the cost of the applying the rule can be computed once at load time, rather than at each
 * time the rule is applied.
 *
 * @author Matt Post <post@cs.jhu.edu>
 * @author Juri Ganitkevich <juri@cs.jhu.edu>
 */

public abstract class DefaultStatelessFF extends FeatureFunction {

  public StatelessFF(WeightVector weights) {
    super(weights);
  }

  public final boolean isStateful() {
    return false;
  }

  /**
   * Return the cost of applying a rule for a particular sentence.  The cost is the inner product of
   * (1) the feature vector of features that fire on this rule and (2) the associated weights from
   * the weight vector.
   *
   * For stateless features, the features can only come from the rule itself, the input sentence,
   * neither, or both.  This function should be overridden to be made more efficient than the hash
   * lookup defined here; this default implementation assumes the feature value is 1 and multiplies
   * it times the weight obtained inefficiently from the hash.
   */
  @Override
  public float computeCost(Rule rule, int sentID) {
    if (name != null) {
      if (weights.containsKey(name))
        return weights.get(name);
    }
    return 0.0;
  }
}
 
